extern printf

section .data
fmt1: db "%d",10,0
fmt2: db "%s",10,0
fmt3: db "%c",10,0

section .bss
a: resb 255

section .text
global main


main:
mov rbx, 5
mov r12, 4
loop1:
mov rbx, 6
mov r12, 4
loop2:
mov rbx, 6
mov r12, 4
cmp r12, rbx
JG loop2
mov rbx, 6
mov r12, 4
cmp r12, rbx
JG loop1
mov rax, 6
mov [a], rax
mov rax, 0
ret
