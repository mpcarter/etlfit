javac -cp C:\dbfit\dbfit-complete-2.0.0-rc5\dbfit\lib\fitnesse-standalone.jar;C:\dbfit\dbfit-complete-2.0.0-rc5\dbfit\lib\fitlibrary-20081102.jar -d classes src\etlfit\RunKettleFixture.java
cd classes
jar -cvf etlfit.jar etlfit
copy etlfit.jar C:\dbfit\dbfit-complete-2.0.0-rc5\dbfit\lib\

