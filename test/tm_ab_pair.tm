# Turing machine that accepts an input as follow:
# L = {w = (a | b | c)* and the combination ab must be pair number }
# 0 is consider as pair
q0 q1 q2 q3
a b c
a b c .
q0
.
q0 q1
q0 a q1 a R
q0 b q0 b R
q0 c q0 c R
q1 a q1 a R
q1 b q2 b R
q1 c q0 c R
q2 a q3 a R
q2 b q2 b R
q2 c q2 c R
q3 a q3 a R
q3 b q0 b R
q3 c q2 c R