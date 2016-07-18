#include <string.h>
#include <jni.h>

jstring Java_mobile_android_jni_helloworld_HelloWorldJni_stringFromJNI(JNIEnv *env, jobject obj)
{
   jclass cls;     
   jfieldID fid;  
   cls = (*env)->GetObjectClass(env, obj);
   fid = (*env)->GetFieldID(env, cls, "name", "Ljava/lang/String;");
   char *str;
   char *str1 =  (char*)(*env)->GetStringUTFChars(env, (*env)->GetObjectField(env, obj, fid), NULL);
   str = (char*)malloc(128);
   memset(str, 0x0, 128);
   strcpy(str, "你好");
   strcat(str, str1);

   return (*env)->NewStringUTF(env, str);
}
