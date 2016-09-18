function Numseq=PWAA(P_SeqFrag)
Len=length(P_SeqFrag);
L=(Len-1)/2;
Loca=[-L:L];
OSet='ACDEFGHIKLMNPQRSTVWY';
for i=1:20
    Numseq(i)=1/(L*(L+1));
    temsum=0;
    for j=1:(2*L+1)
        temsign=(OSet(i)==P_SeqFrag(j));
        temsum=temsum+temsign*(Loca(j)+abs(Loca(j))/L);
    end
     Numseq(i)=Numseq(i)*temsum;
 end
 return
 