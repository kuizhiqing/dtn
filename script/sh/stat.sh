#########################################################################
# File Name: stat.sh
# Author: kuizhiqing
# mail: kuizhiqing@msn.com
# Created Time: Fri Nov 21 15:15:59 2014
#########################################################################
#!/bin/bash

echo 'all contact count :' `grep up $1 | wc -l`
echo $2' and '$3' contact count :' `grep "up" $1 | grep "n$2 " | grep "n$3 " | wc -l`

