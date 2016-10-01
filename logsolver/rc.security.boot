#!/usr/bin/ksh
# IBM_PROLOG_BEGIN_TAG 
# This is an automatically generated prolog. 
#  
# bos61H src/bos/etc/rc.security.boot/rc.security.boot.sh 1.1.1.5 
#  
# Licensed Materials - Property of IBM 
#  
# Restricted Materials of IBM 
#  
# COPYRIGHT International Business Machines Corp. 2006,2008 
# All Rights Reserved 
#  
# US Government Users Restricted Rights - Use, duplication or 
# disclosure restricted by GSA ADP Schedule Contract with IBM Corp. 
#  
# @(#)07 1.1.1.5 src/bos/etc/rc.security.boot/rc.security.boot.sh, cmdsrbac, bos61H, 0906A_61H 12/18/08 06:01:10
# IBM_PROLOG_END_TAG 

umask 027
unset PATH
export PATH=/usr/bin:/etc:/usr/sbin:/sbin:/usr/ucb
AUTHUPDFL=/etc/security/.authupd
DOTKST=/etc/security/.kst

if [ -f $AUTHUPDFL ]
then
	rm -f $AUTHUPDFL $DOTKST
fi

#
# Load Trusted Execution policies into kernel. Works for
# both WPAR and non-WPAR env. Simply ignore in case of
# any error and continue further with the script.
#
/usr/sbin/trustchk -b 3

#
# Load the information from the security databases into the
# Kernel Security Tables.
#
if [ "$1" == "-r" ]; then
#################################################################
## Calling this script with option -r has a special significance
## It is intended for WPAR mobility. Do not edit this section.
#################################################################

	/usr/sbin/setkst -n -b -q

	if [ $? -eq 0 ]; then 
		exit 0
	else
		exit 1
	fi
#################################################################
## Section over.
#################################################################
fi
/usr/sbin/setkst -x -q

#
# Upload secconf to kernel and set runmode to OPERATION
#
if [ ! -f /etc/rc.mls.boot ]
then
/usr/sbin/setsecconf -o
/usr/sbin/setrunmode -o
fi

exit 0
