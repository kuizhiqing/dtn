#!/usr/bin/python

import sys

inf = open(sys.argv[1],'r')
outf = open(sys.argv[2],'w')
line = inf.readlines()
#print line
xs = line[0].split(',')
yy = 0
print len(xs)
for i in range(len(xs)-2) :
	#print xs[i+1]+'-'+xs[i]
	k = float(xs[i+1])-float(xs[i])
	if k!=0 :
		yy = 1/(k)
	outf.write(str(yy)+',')
outf.write(str(yy))