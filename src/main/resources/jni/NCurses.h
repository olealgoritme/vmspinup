 #include "jni.h"
 /* Header for class NCurses */

 #ifndef _Included_NCurses
 #define _Included_NCurses
 #ifdef __cplusplus
 extern "C" {
    #endif
 /*
    11 * Class: NCurses
    12 * Method: install
    13 * Signature: ()I
    14 */
 JNIEXPORT jint JNICALL Java_NCurses_install
 (JNIEnv *, jclass);

 /*
    19 * Class: NCurses
    20 * Method: uninstall
    21 * Signature: ()I
    22 */
 JNIEXPORT jint JNICALL Java_NCurses_uninstall
 (JNIEnv *, jclass);

 /*
    27 * Class: NCurses
    28 * Method: setCharAt
    29 * Signature: (III)V
    30 */
 JNIEXPORT void JNICALL Java_NCurses_setCharAt
 (JNIEnv *, jclass, jint, jint, jint);

 /*
    35 * Class: NCurses
    36 * Method: refresh
    37 * Signature: ()V
    38 */
 JNIEXPORT void JNICALL Java_NCurses_refresh
 (JNIEnv *, jclass);

 /*
    43 * Class: NCurses
    44 * Method: getRowCount
    45 * Signature: ()I
    46 */
 JNIEXPORT jint JNICALL Java_NCurses_getRowCount
 (JNIEnv *, jobject);

 /*
    51 * Class: NCurses
    52 * Method: getColumnCount
    53 * Signature: ()I
    54 */
 JNIEXPORT jint JNICALL Java_NCurses_getColumnCount
 (JNIEnv *, jobject);

 /*
    59 * Class: NCurses
    60 * Method: standout
    61 * Signature: ()I
    62 */
 JNIEXPORT jint JNICALL Java_NCurses_standout
 (JNIEnv *, jobject);

 /*
    67 * Class: NCurses
    68 * Method: standend
    69 * Signature: ()I
    70 */
 JNIEXPORT jint JNICALL Java_NCurses_standend
 (JNIEnv *, jobject);

 #ifdef __cplusplus
 }
 #endif
 #endif
