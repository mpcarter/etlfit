#!/bin/bash

cd classes
jar -cvf etlfit.jar etlfit
cp etlfit.jar ~/dbfit/lib/
cd ..

