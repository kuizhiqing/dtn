#!/usr/bin/python


import string
import matplotlib.pyplot as plt
y = [1,2,3,4,5,3,4,5,6]
plt.rc('font',size =18)
plt.plot(y) 
plt.title('contact frequency of all nodes') 
plt.xlabel('time window') 
plt.ylabel('contact frequency') 
plt.show()
#plt.savefig('contact_frequency_1.png')
