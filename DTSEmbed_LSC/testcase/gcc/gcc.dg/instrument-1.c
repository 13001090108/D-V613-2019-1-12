/* { dg-do compile } */
/* { dg-options "-finstrument-functions" } */

void fn () { }

/* { dg-final { scan-assembler "__cyg_profile_func_enter" } } */
/* { dg-final { scan-assembler "__cyg_profile_func_exit" } } */
