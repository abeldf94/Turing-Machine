# Turing machine that first copy the 1's
# and after copy the 0's
# It leave a white symbol between
# Example: 101001 -> 101001.111.000
q0 q1 q2 q3 q4 q5 q6 q7 q8 q9 q10 q11 q12 q13 q14
0 1 X Y
0 1 X Y .
q0
.
q14
q0 0 q0 0 R
q0 1 q1 X R
q0 . q5 . L
q1 0 q1 0 R
q1 1 q1 1 R
q1 . q2 . R
q2 1 q2 1 R
q2 . q3 1 L
q3 1 q3 1 L
q3 . q4 . L
q4 0 q4 0 L
q4 1 q4 1 L
q4 X q0 X R
q5 0 q5 0 L
q5 X q5 X L
q5 . q6 . R
q6 X q6 X R
q6 0 q7 Y R
q6 . q13 . L
q7 0 q7 0 R
q7 X q7 X R
q7 . q8 . R
q8 1 q8 1 R
q8 . q9 . R
q9 0 q9 0 R
q9 . q10 0 L
q10 0 q10 0 L
q10 . q11 . L
q11 1 q11 1 L
q11 . q12 . L
q12 X q12 X L
q12 0 q12 0 L
q12 Y q6 Y R
q13 Y q13 0 L
q13 X q13 1 L
q13 . q14 . R
