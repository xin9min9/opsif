#!/usr/bin/ksh
# IBM_PROLOG_BEGIN_TAG 
# This is an automatically generated prolog. 
#  
# bos610 src/bos/usr/sbin/C2admin/rc.CC.sh 1.2 
#  
# Licensed Materials - Property of IBM 
#  
# Restricted Materials of IBM 
#  
# COPYRIGHT International Business Machines Corp. 2002 
# All Rights Reserved 
#  
# US Government Users Restricted Rights - Use, duplication or 
# disclosure restricted by GSA ADP Schedule Contract with IBM Corp. 
#  
# @(#)95	1.2  src/bos/usr/sbin/C2admin/rc.CC.sh, cmdsadm, bos610 9/4/02 16:38:02
# IBM_PROLOG_END_TAG 
#
# rc.CC
#
# Mount shared identification and authentication data
# from the CC administrative host.
#

umask 027
unset PATH
export PATH=/usr/bin:/etc:/usr/sbin:/sbin:/usr/ucb

[[ -s /etc/security/admin_host ]] && {

	# change xti and pty permissions
	#
        chmod 700 /dev/xti
        chmod 600 /dev/xti/*
        for pty in /dev/ptyp* /dev/ttyp* ; do
                chmod 000 $pty
        done

        # clear all ACLs and privileged ports
        #
        dacinet aclflush

        # make rpc.statd available to NFS clients
        #
        dacinet acladd \
        $(rpcinfo -p localhost | awk '$3=="tcp" && $5=="status" { print $4 }') \
        ::/0 u:root

        mount /etc/data.shared
}


