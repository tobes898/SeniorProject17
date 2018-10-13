#!/bin/bash
java -jar isc.jar
nasm -f elf64 source.asm
clang -o source source.o
./source