#!/usr/bin/sh -
# IBM_PROLOG_BEGIN_TAG 
# This is an automatically generated prolog. 
#  
# bos610 src/bos/usr/sbin/sliplogin/slip.login 1.4 
#  
# Licensed Materials - Property of IBM 
#  
# COPYRIGHT International Business Machines Corp. 1993,2006 
# All Rights Reserved 
#  
# US Government Users Restricted Rights - Use, duplication or 
# disclosure restricted by GSA ADP Schedule Contract with IBM Corp. 
#  
# @(#)24	1.4  src/bos/usr/sbin/sliplogin/slip.login, cmdnet, bos610 8/16/06 16:16:05
# IBM_PROLOG_END_TAG 
# 
# COMPONENT_NAME: CMDNET
# 
# FUNCTIONS: 
#
# ORIGINS: 26  27
#
# (C) COPYRIGHT International Business Machines Corp. 1993
# All Rights Reserved
# Licensed Materials - Property of IBM
#
# US Government Users Restricted Rights - Use, duplication or
# disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
#
#	@(#)slip.login	5.1 (Berkeley) 7/1/90

#
# generic login file for a slip line.  sliplogin invokes this with
# the parameters:
#      1        2         3        4          5         6     7-n
#   slipunit ttyspeed loginname local-addr remote-addr mask opt-args
#
/usr/sbin/ifconfig sl$1 inet $4 $5 netmask $6 up > /dev/null 2>&1
/usr/sbin/strinfo -m | /usr/bin/grep "\'slip\'" > /dev/null 2>&1 || \
	      /usr/sbin/strload -m /usr/lib/drivers/slip > /dev/null 2>&1
exit

