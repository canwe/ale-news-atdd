#!/bin/bash
echo "[INFO] Making ALE News components"

echo "[INFO] Making Web Services & Application"
cd web
mvn install
if test $? -eq 0; then
	SUCCESS_WEB=true
	echo "[INFO] Making IOS Application"
	cd ../ios/ALE-News
	xcodebuild build -sdk iphonesimulator8.1 -configuration Debug CODE_SIGN_IDENTITY="" CODE_SIGNING_REQUIRED=NO
	if test $? -eq 0; then
		SUCCESS_IOS=true
	else
		SUCCESS_IOS=false
	fi
	
	cucumber features
	if test $? -eq 0; then
		SUCCESS_IOS_CUCUMBER=true
	else
		SUCCESS_IOS_CUCUMBER=false
	fi
else
	SUCCESS_WEB=false
fi

echo "[INFO] ------------------------------------------------------------------------"
if [ $SUCCESS_WEB == true ]; then
	echo "[INFO] Web Services & Application ..... SUCCESS"
	BUILD_SUCCESS=true
else
	echo "[INFO] Web Services & Application ..... FAILURE"
	BUILD_SUCCESS=false
fi
if [ $SUCCESS_IOS == true ]; then
	echo "[INFO] IOS Application ................ SUCCESS"
	BUILD_SUCCESS=true
else
	echo "[INFO] IOS Application ................ FAILURE"
	BUILD_SUCCESS=false
fi
if [ SUCCESS_IOS_CUCUMBER == true ]; then
	echo "[INFO] IOS Application Cucumber ....... SUCCESS"
	BUILD_SUCCESS=true
else
	echo "[INFO] IOS Application Cucumber ....... FAILURE"
	BUILD_SUCCESS=false
fi
echo "[INFO] ------------------------------------------------------------------------"

if [ $BUILD_SUCCESS == true ]; then
	echo "[INFO] BUILD SUCCESS"
else
	echo "[INFO] BUILD FAILED"
fi
echo "[INFO] ------------------------------------------------------------------------"
