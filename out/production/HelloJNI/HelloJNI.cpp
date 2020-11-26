// Save as "HelloJNI.cpp"
#include <jni.h>       // JNI header provided by JDK
#include <iostream>    // C++ standard IO header
#include <cmath>
#include "HelloJNI.h"  // Generated
using namespace std;

// Implementation of the native method sayHello()
JNIEXPORT void JNICALL Java_HelloJNI_sayHello(JNIEnv *env, jobject thisObj) {
	cout << "Hello World from C++!" << endl;
   return;
}

JNIEXPORT jdouble JNICALL Java_HelloJNI_calculateSTDDev(JNIEnv *env, jobject thisObj, jintArray inJNIArray){
    jdouble mean=Java_HelloJNI_calculateMean(env,thisObj,inJNIArray);
    jint *inCArray = env->GetIntArrayElements(inJNIArray, NULL);
    if (NULL == inCArray) return 0;
    jsize length = env->GetArrayLength(inJNIArray);
    jdouble var=0.0;
    for(int i = 0; i <length; ++i)
        var += pow(inCArray[i] - mean, 2);
    env->ReleaseIntArrayElements(inJNIArray, inCArray, 0); // release resources
    jdouble std=sqrt(var / 10);
    return std;
}

JNIEXPORT jdouble JNICALL Java_HelloJNI_calculateMean(JNIEnv *env, jobject thisObj, jintArray inJNIArray){
    jint *inCArray = env->GetIntArrayElements(inJNIArray, NULL);
    if (NULL == inCArray) return 0;
    jsize length = env->GetArrayLength(inJNIArray);
    // Step 2: Perform its intended operations
    jint sum = 0;
    int i;
    for (i = 0; i < length; i++) {
        sum += inCArray[i];
    }
    jdouble average = (jdouble)sum / length;
    env->ReleaseIntArrayElements(inJNIArray, inCArray, 0); // release resources

    return average;
}