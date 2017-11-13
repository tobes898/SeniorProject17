extern printf

section .data
fmt1: db "%d",10,0
fmt2: db "%s",10,0
fmt3: db "%c",10,0

section .bss
a: resb 255
b: resb 255

section .text
global main

c:
mov rdi, fmt2
mov rsi, a
mov rax, 0
call printf
ret

main:
mov rax, "hi world"
mov [a], rax
mov rax, 6
mov [b], rax
mov rbx, [b]
mov r12, 8
loop1:
call c
mov rax, [b]
inc rax
mov [b], rax
mov rbx, [b]
mov r12, 8
cmp r12, rbx
JG loop1
mov rax, 0
ret
