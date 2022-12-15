if not defined in_subprocess (cmd /k set in_subprocess=y ^& %0 %*) & exit )

CALL docker build -t gamerdates-api-auth .
.\run.bat

@pause
