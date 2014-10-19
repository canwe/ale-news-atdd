#!/bin/bash
echo "[INFO] Making ALE News components"

echo "[INFO] Making Web Services & Application"
cd web
mvn install
if test $? -eq 0; then
	SUCCESS_WEB=true
	echo "[INFO] Making IOS Application"
	cd ../ios/ALE-News
	xcodebuild -scheme ALE-News build

	if test $? -eq 0; then
		SUCCESS_IOS=true
	else
		SUCCESS_IOS=false
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
echo "[INFO] ------------------------------------------------------------------------"

if [ $BUILD_SUCCESS == true ]; then
	echo "[INFO] BUILD SUCCESS"
else
	echo "[INFO] BUILD FAILED"
fi
echo "[INFO] ------------------------------------------------------------------------"