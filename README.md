# Turing Machine
A Turing machine is a mathematical model of computation that defines an abstract machine, which manipulates symbols on a strip of tape according to a table of rules. Despite the model's simplicity, given any computer algorithm, a Turing machine capable of simulating that algorithm's logic can be constructed.

Turing machine can be formally defined as a 7-tuple:

TM = (Q, Σ, Γ, s, F, δ)

* Q: Finite set of states
* Σ: Input alphabet
* Γ: Σ ∈ Γ Tape alphabet
* s: s ∈ Q is the initial state
* b: b ∈ Γ is the white symbol
* F: F ⊆ Q is the set of accepting states
* δ: Q x Γ -> Q x Γ x {L, R} set of transitions between states

## Implementation
This program simulates the behaviour of a Turing machine with infinite tape and one head that moves left, right or stop. It loads a file that contains the machine and it shows a simple menu that is easily understood. You can change the input tape all the times you want in the same execution and compute it.

## How to use
`java -jar TMSimulator.jar file.tm`

Where:
- file.tm: it's a file with custom input.

If you want to know how to build a file, you can look ![here](https://github.com/alu0100792218/Turing-Machine/blob/master/report/Practica2.pdf). It's in Spanish
