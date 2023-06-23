package com.hygie.hygietask.tasks;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;

import org.apache.commons.io.FileUtils;

import com.hygie.hygietask.model.ResultTask;
import com.hygie.hygietask.model.TaskClass;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

@AllArgsConstructor
@Slf4j
public class CheckTotalRam implements TaskClass {

    private String[] args;

    @Override
    public ResultTask executer() {
        ResultTask resultTask = new ResultTask();

        verifyArgs();

        log.debug("Argument ajouté : {}", args[0]);

        int minRamSize = Integer.parseInt(args[0]);
        
        
        
        SystemInfo systemInfo = new SystemInfo();
        GlobalMemory globalMemory = systemInfo.getHardware().getMemory();
        
        long totalPhysicalMemory = globalMemory.getTotal();
        
       
       
        long totalPhysicalMemoryInGigabytes =totalPhysicalMemory/FileUtils.ONE_GB; // Conversion d'octets en gigaoctets en utilisant Apache Commons IO

        log.info("Quantité totale de RAM relevée par Java : " + totalPhysicalMemory + " octets (" + totalPhysicalMemoryInGigabytes + " Go)");
        log.info("Taille minimale spécifiée : " + minRamSize + " Go");

        if (totalPhysicalMemoryInGigabytes >= minRamSize) {
            resultTask.setSuccessfulTest(true);
        } else {
            resultTask.setSuccessfulTest(false);
        }
        resultTask.setResult("La machine dispose de " + totalPhysicalMemoryInGigabytes + " Go de RAM");

        log.info("La quantité de RAM disponible est suffisante.");

        return resultTask;
    }

    @Override
    public void verifyArgs() {
        if (args.length != 1) {
            throw new IllegalArgumentException("Le tableau doit contenir exactement un élément.");
        }

        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("L'élément du tableau doit être un nombre entier.");
        }
    }
}
