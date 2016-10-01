#!/bin/bash
a01n15(){
os_type=`uname -s`
case $os_type in
    AIX)
        lsvg -o
        ;;
    Linux)
        vgdisplay -A |grep 'VG Name' | awk '{print $NF}' 2>/dev/null
        ;;
    *)
        break
        ;;
esac
}
a01n16(){
os_type=`uname -s`
case $os_type in
    AIX)
        df -vg
        ;;
    Linux)
        df -m
        ;;
    *)
        break
        ;;
esac
}
a01n17(){
env
}
a01n18(){
cat /etc/hosts|egrep -v '^#|^$|127.0.0.1|::1'
}
a01n19(){
ulimit -a 
}
a01n20(){
crontab -l |grep -v '^#'
}
