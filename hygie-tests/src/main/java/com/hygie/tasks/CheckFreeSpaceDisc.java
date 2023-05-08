package com.hygie.tasks;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

import com.hygie.model.ResultTask;
import com.hygie.model.TaskClass;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class CheckFreeSpaceDisc implements TaskClass {
	
	private String[] args;

	@Override
	public ResultTask executer() {
		
		ResultTask resultTask = new ResultTask();
		
		verifyArgs();
		
		log.debug("add argument {}",args[0]);
		
		int minTargetDiskSize = Integer.parseInt(args[0]);
        long minTargetDiskSizeInBytes = FileUtils.ONE_GB * minTargetDiskSize; // Conversion de Go en octets en utilisant Apache Commons IO
        Path path = Path.of(".");
        
        try {
            FileStore fileStore = Files.getFileStore(path);
            long usableSpaceInBytes = fileStore.getUsableSpace();
            
            log.info("Espace libre relevé par Java : " + usableSpaceInBytes + " octets (" + (usableSpaceInBytes / FileUtils.ONE_GB) + " Go)");
            log.info("Taille minimale spécifiée : " + minTargetDiskSizeInBytes + " octets (" + minTargetDiskSize + " Go)");

            if (usableSpaceInBytes > minTargetDiskSizeInBytes) {            
                resultTask.setSuccessfulTest(true);
            }else {          	
            	resultTask.setSuccessfulTest(false);
            }
            resultTask.setResult("le disque à "+(usableSpaceInBytes / FileUtils.ONE_GB)+" Go d'espace libre");
            log.info("L'espace libre du disque est suffisant.");
            
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        
		
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
