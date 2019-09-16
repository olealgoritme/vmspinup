#include "NCurses.h"
#include <string.h>
#include <curses.h>
#include <signal.h>

        JNIEXPORT jint JNICALL Java_NCurses_install(JNIEnv *env, jclass clazz) {
            //(void) signal(SIGINT, finish); /* arrange interrupts to terminate */

            (void) initscr(); /* initialize the curses library */
            keypad(stdscr, TRUE); /* enable keyboard mapping */
            (void) nonl(); /* tell curses not to do NL->CR/NL on output */
            (void) cbreak(); /* take input chars one at a time, no wait for \n */
            (void) noecho(); /* don't echo input */
            clear();
            move(0,0);
            refresh();
            return 0;
        }

        JNIEXPORT jint JNICALL Java_NCurses_uninstall(JNIEnv *env, jclass clazz) {
            endwin();
            return 0;
        }

        JNIEXPORT jint JNICALL Java_NCurses_getRowCount(JNIEnv *env, jobject obj) {
            int h, w;
            getmaxyx(stdscr, h, w);
            return h;
        }


        JNIEXPORT jint JNICALL Java_NCurses_getColumnCount(JNIEnv *env, jobject obj) {
            int h, w;
            getmaxyx(stdscr, h, w);
            return w;
        }

        JNIEXPORT void JNICALL Java_NCurses_setCharAt (JNIEnv *env, jclass object, jint x, jint y, jint chr) {
            mvaddch(y,x,chr);
        }

        JNIEXPORT void JNICALL Java_NCurses_refresh (JNIEnv *env, jclass object) {
            refresh();
        }

        JNIEXPORT jint JNICALL Java_NCurses_standout (JNIEnv *env, jobject object) {
            standout();
            return 0;
        }

        JNIEXPORT jint JNICALL Java_NCurses_standend (JNIEnv * env, jobject object) {
            standend();
            return 0;
        }
