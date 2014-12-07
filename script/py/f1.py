#!/usr/bin/python


import string
import matplotlib.pyplot as plt
fileName = raw_input('input file name:') 
timeGap = raw_input('input time gap:')# seconds 
windowCount =1
contactFrequency =0
contactFrequencyList =[]
f = file(fileName)
while True:
  line = f.readline() 
  if len(line)==0:
    break;
  items = line.split()
  contactTime = string.atof(items[0])
  if contactTime > windowCount * timeGap: 
    windowCount = windowCount+1
    contactFrequencyList.append(contactFrequency)
    contactFrequency =0 
  else:
    contactFrequency = contactFrequency +1 
f.close()
plt.rc('font',size =18)
plt.plot(contactFrequencyList) 
plt.title('contact frequency of all nodes') 
plt.xlabel('time window') 
plt.ylabel('contact frequency') 
plt.show()
plt.savefig('contact_frequency_1.png')
