EtlFit is a simple fixture extension of FitNesse 
designed to enable the execution of non-database residing 
ETL tools.

Currently it only supports Pentaho Data Integration aka Kettle.

### Install ###

1. Download the latest release.
2. Extract the etlfit.jar file
3. Copy etlfit.jar to lib/ of your DbFit installation

### How to Build ###

It's pretty simple really. Just clone this repo and run the build.bat or build.sh script.  

Then you just need to jar it. (See deploy.bat)
```
cd classes
jar -cvf etlfit.jar etlfit
```

Copy the resulting jar file to the lib directory of FitNesse or DbFit.

You can copy the provided EtlFit subwiki to your FitNesseRoot directory.

Check out the EtlFit.RunKettleTest page for a demonstration.
    
### Before you build ###

You need the Java Development Kit of course.  

EtlFit was inspired and intended to be used with DbFit. 
The current build scripts use the fitnesse and fitlibrary jars from 
that project which you will need in order to build this.
