The Uniwork project represents the common framework to realize some java projects...

Files
=====
    
    Files:
        .acf    Application Configuration File (txt)
        .adf    Application Definition File (xml)
        .amcf   Application Module Configuration File (txt)        
        .amdf   Application Module Definition File (xml)

Configuration (.acf)
====================

    -ConsoleShowLog
        -Show the log into the java console
    -ConsoleShowLogEntrySource
        -Show the entry source from log
    -Debuglevel
        -Sets the debug level, default value is 0
    -TerminateQuestion
        -Show the question when you will terminate the application
    -DefinitionFilename
            -Sets the definition filename (e.g. resources/runtime/definition.adf)   
        
Configuration (.amcf)
=====================

    -DefinitionFilename
        -Sets the module definition filename

Application Modules
===================

###Console
-   Configuration (.amcf)
       -    Width
            -   Stage width
       -    Height
            -   Stage height
       -    PosX
            -   Shows the console stage at Pos X   
       -    PosY
            -   Shows the console stage at Pos Y   
       -    LogDescending
            -   Shows the log descending or ascending
       -    ShowCommandArea
            -   Shows the command area      


Version History
===============

      Mai 2015      NGApplication support
    27.07.2015      ORB used in NGApplication
    22.08.2015      UI package and first toolbox implementation
    30.08.2015      Multi toolbox managing
    10.09.2015      Application definition
    30.09.2015      Application object resolving
    29.10.2015      First Test's in Teamcity are running
    28.11.2015      Animation Manager
    30.11.2015      StageSceneGraph
    05.08.2017      Console application module
    09.08.2017      Console manager