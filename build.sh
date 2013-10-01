#!/bin/bash

_HOME=~/

javac -cp .:$_HOME/dbfit/lib/fitnesse-standalone.jar:$_HOME/dbfit/lib/fitlibrary-20081102.jar -d classes src/etlfit/RunKettleFixture.java

