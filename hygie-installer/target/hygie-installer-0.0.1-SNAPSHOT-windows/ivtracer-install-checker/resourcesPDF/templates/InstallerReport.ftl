= {blank}
:notitle:

[frame=none, grid=none, cols="2a,3a"]
[cols="1a,2a"]
|===

| 

[cols="1,1,1"]
!===
!
^![quality]#Checker#
^![quality]#Approver#
![quality]#Last Name#
!
!
![quality]#First Name#
!
!
![quality]#Date#
!
!
![quality]#Signature or initials#  [point-virgule-necessaire]#&#10; ;#
! &#160; &#10; &#10;
! &#160; &#10; &#10;
!===

|

[frame=none, grid=none, cols="1,1,9a"]
!===
!
!
![quality]#Annotations :#

!
!
!


!
!
!

[frame="topbot",grid="rows"]
:===
: &#160; &#10; &#10;
: &#160; &#10; &#10;
: &#160; &#10; &#10;
:===

!===

|===



[options="header", frame=none, grid=none]
|===
|[heading-title]#Site  :#
|===




[options="header", frame=none, grid=none]
|===
|[heading-title]#Settings  :#
|===

[grid=none,rows="1,1,1,1" cols="7,1,16"]
|===
|[quality]#date and hours :#
|
|[quality]#${Date}#
|[quality]#component :#
|
|[quality]#${InstallReport.component}#
|
|
|
<#list InstallReport.getTableTaskresultSeparated().getVarInventory() as k,v> 
    |[quality]#${k} :# 
    |
    |[quality]#${v?string}#

</#list> 
|
|===



[heading-title]#Internal prerequisites#

<#if InstallReport.getTableTaskresultSeparated().getInternalTaskResultsMaterialSort()?size gt 0>

[heading-subtitle]#Hardware#
[options="header",cols="2,1,4,1"]
|===
^|Tested elements
^|Conformity
^|Details
^|Time

<#list InstallReport.getTableTaskresultSeparated().getInternalTaskResultsMaterialSort() as integrationTaskResultPort>

|<#if integrationTaskResultPort.getTaskResult().get().isSuccess() ==false>[error]#</#if>${integrationTaskResultPort.getTaskDescriptor().getName()}<#if integrationTaskResultPort.getTaskResult().get().isSuccess()==false>#</#if>

^|<#if integrationTaskResultPort.getTaskResult().get().isSuccess() ==false>[error]#K.O#</#if><#if integrationTaskResultPort.getTaskResult().get().isSuccess()==true>O.K</#if>

|<#if integrationTaskResultPort.getTaskResult().get().isSuccess() ==false>[error]#${integrationTaskResultPort.getTaskResult().get().returnedValue}# </#if><#if integrationTaskResultPort.getTaskResult().get().isSuccess()==true>${integrationTaskResultPort.getTaskResult().get().returnedValue}</#if>

^|<#if integrationTaskResultPort.getTaskResult().get().isSuccess() ==false>[error]#</#if>${integrationTaskResultPort.getTaskDescriptor().getHoursInString()}<#if integrationTaskResultPort.getTaskResult().get().isSuccess() ==false>#</#if>

</#list>
|===
</#if>

<#if InstallReport.getTableTaskresultSeparated().getInternalTaskResultsPortSort()?size gt 0>

[heading-subtitle]#Ports#

[options="header",cols="3,4,1"]
|===
^|Tested elements
^|Conformity
^|Time
<#list InstallReport.getTableTaskresultSeparated().getInternalTaskResultsPortSort() as taskResultPort>
|<#if taskResultPort.getTaskResult().get().isSuccess() ==false>[error]#</#if>${taskResultPort.getTaskDescriptor().getName()}<#if taskResultPort.getTaskResult().get().isSuccess()==false>#</#if>
^|<#if taskResultPort.getTaskResult().get().isSuccess() ==false>[error]#K.O : ${taskResultPort.getTaskResult().get().returnedValue}# </#if><#if taskResultPort.getTaskResult().get().isSuccess()==true>O.K</#if>
^|<#if taskResultPort.getTaskResult().get().isSuccess() ==false>[error]#</#if>${taskResultPort.getTaskDescriptor().getHoursInString()}<#if taskResultPort.getTaskResult().get().isSuccess() ==false>#</#if>



</#list>
|===

</#if>
<#if InstallReport.getTableTaskresultSeparated().getInternalTaskResultsUncategorizedSort()?size gt 0>

[heading-subtitle]#Uncategorized#
[options="header",cols="2,1,4,1"]
|===
^|Tested elements
^|Conformity
^|Details
^|Time

<#list InstallReport.getTableTaskresultSeparated().getInternalTaskResultsUncategorizedSort() as taskResultUncategorized>

|<#if taskResultUncategorized.getTaskResult().get().isSuccess() ==false>[error]#</#if>${taskResultUncategorized.getTaskDescriptor().getName()}<#if taskResultUncategorized.getTaskResult().get().isSuccess()==false>#</#if>
^|<#if taskResultUncategorized.getTaskResult().get().isSuccess() ==false>[error]#K.O#</#if><#if taskResultUncategorized.getTaskResult().get().isSuccess()==true>O.K</#if>
|<#if taskResultUncategorized.getTaskResult().get().isSuccess() ==false>[error]#${taskResultUncategorized.getTaskResult().get().returnedValue}# </#if><#if taskResultUncategorized.getTaskResult().get().isSuccess()==true>${taskResultUncategorized.getTaskResult().get().returnedValue}</#if>
^|<#if taskResultUncategorized.getTaskResult().get().isSuccess() ==false>[error]#</#if>${taskResultUncategorized.getTaskDescriptor().getHoursInString()}<#if taskResultUncategorized.getTaskResult().get().isSuccess() ==false>#</#if>

</#list>
|===
</#if>

[quality]#Number of success tasks : ${InstallReport.getTableTaskresultSeparated().getInternalTaskSuccess()} / ${InstallReport.getTableTaskresultSeparated().getTotalInternalTask()}          (${InstallReport.getTableTaskresultSeparated().getInternalTaskSuccess() / InstallReport.getTableTaskresultSeparated().getTotalInternalTask()*100}%)#

<#if InstallReport.getTableTaskresultSeparated().getIntegationTaskResultsBefortSort()?size gt 0>

[heading-title]#Integration prerequisites#

<#if InstallReport.getTableTaskresultSeparated().getIntegrationTaskResultsPortSort()?size gt 0>

[heading-subtitle]#Ports#
[options="header",cols="3,4,1"]
|===
^|Tested elements
^|Conformity
^|Time

<#list InstallReport.getTableTaskresultSeparated().getIntegrationTaskResultsPortSort() as integrationTaskResultPort>
|<#if integrationTaskResultPort.getTaskResult().get().isSuccess() ==false>[error]#</#if>${integrationTaskResultPort.getTaskDescriptor().getName()}<#if integrationTaskResultPort.getTaskResult().get().isSuccess()==false>#</#if>
^|<#if integrationTaskResultPort.getTaskResult().get().isSuccess() ==false>[error]#K.O : ${integrationTaskResultPort.getTaskResult().get().returnedValue}# </#if><#if integrationTaskResultPort.getTaskResult().get().isSuccess()==true>O.K</#if>
^|<#if integrationTaskResultPort.getTaskResult().get().isSuccess() ==false>[error]#</#if>${integrationTaskResultPort.getTaskDescriptor().getHoursInString()}<#if integrationTaskResultPort.getTaskResult().get().isSuccess() ==false>#</#if>

</#list>
|===
</#if>

<#if InstallReport.getTableTaskresultSeparated().getIntegrationTaskResultsActiveDirectorySort()?size gt 0>

[heading-subtitle]#LDAP#
[options="header",cols="2,1,4,1"]
|===
^|Tested elements
^|Conformity
^|Details
^|Time

<#list InstallReport.getTableTaskresultSeparated().getIntegrationTaskResultsActiveDirectorySort() as taskResultLdap>

|<#if taskResultLdap.getTaskResult().get().isSuccess() ==false>[error]#</#if>${taskResultLdap.getTaskDescriptor().getName()}<#if taskResultLdap.getTaskResult().get().isSuccess()==false>#</#if>
^|<#if taskResultLdap.getTaskResult().get().isSuccess() ==false>[error]#K.O#</#if><#if taskResultLdap.getTaskResult().get().isSuccess()==true>O.K</#if>
|<#if taskResultLdap.getTaskResult().get().isSuccess() ==false>[error]#${taskResultLdap.getTaskResult().get().returnedValue}# </#if><#if taskResultLdap.getTaskResult().get().isSuccess()==true>${taskResultLdap.getTaskResult().get().returnedValue}</#if>
^|<#if taskResultLdap.getTaskResult().get().isSuccess() ==false>[error]#</#if>${taskResultLdap.getTaskDescriptor().getHoursInString()}<#if taskResultLdap.getTaskResult().get().isSuccess() ==false>#</#if>

</#list>
|===

</#if>
<#if InstallReport.getTableTaskresultSeparated().getIntegrationTaskResultsUncategorizedSort()?size gt 0>

[heading-subtitle]#Uncategorized#
[options="header",cols="2,1,4,1"]
|===
^|Tested elements
^|Conformity
^|Details
^|Time

<#list InstallReport.getTableTaskresultSeparated().getIntegrationTaskResultsUncategorizedSort() as taskResultUncategorized>

|<#if taskResultUncategorized.getTaskResult().get().isSuccess() ==false>[error]#</#if>${taskResultUncategorized.getTaskDescriptor().getName()}<#if taskResultUncategorized.getTaskResult().get().isSuccess()==false>#</#if>
^|<#if taskResultUncategorized.getTaskResult().get().isSuccess() ==false>[error]#K.O#</#if><#if taskResultUncategorized.getTaskResult().get().isSuccess()==true>O.K</#if>
|<#if taskResultUncategorized.getTaskResult().get().isSuccess() ==false>[error]#${taskResultUncategorized.getTaskResult().get().returnedValue}# </#if><#if taskResultUncategorized.getTaskResult().get().isSuccess()==true>${taskResultUncategorized.getTaskResult().get().returnedValue}</#if>
^|<#if taskResultUncategorized.getTaskResult().get().isSuccess() ==false>[error]#</#if>${taskResultUncategorized.getTaskDescriptor().getHoursInString()}<#if taskResultUncategorized.getTaskResult().get().isSuccess() ==false>#</#if>

</#list>
|===
</#if>


[quality]#Number of success tasks : ${InstallReport.getTableTaskresultSeparated().getIntegrationTaskSuccess()} / ${InstallReport.getTableTaskresultSeparated().getTotalIntegrationTask()}          (${InstallReport.getTableTaskresultSeparated().getIntegrationTaskSuccess() / InstallReport.getTableTaskresultSeparated().getTotalIntegrationTask()*100}%)#

</#if>





