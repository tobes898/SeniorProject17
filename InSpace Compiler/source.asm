extern printf

section .data
fmt1: db "%d",10,0
fmt2: db "%s",10,0
fmt3: db "%c",10,0

section .bss

section .text
global main


main:
mov rax, 0
ret
