import random
import sys

R = 150
C = 150
print(1)
print(R), 
#print(" "),
print(C)

for i in range(R):
	for j in range(C):
		sys.stdout.write(str(random.randint(0,1))),
	print("")