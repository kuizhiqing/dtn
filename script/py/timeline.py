#!/usr/bin/python

import sys

inf = open(sys.argv[1],'r')
outf = open(sys.argv[2],'w')
for line in inf.readlines():
	#print line
	outf.write(line.split(' ')[0]+',')
