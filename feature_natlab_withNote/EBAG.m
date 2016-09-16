function Numseq=EBAG(ProteinSeq)
Len=length(ProteinSeq);
C{1}='AFGILMPVW';
C{2}='CNQSTY';
C{3}='DE';
C{4}='HKR';
%OSet='ACDEFGHIKLMNPQRSTVWY';
%  BM = blosum(blosumnum,'Order',OSet);
%  Len=length(ProteinSeq);
 %Numseq=zeros(1,4*Len);
 Numseq=[];
 for j=1:4
     for i=1:Len
          Numseq=[Numseq,ismember(ProteinSeq(i),C{j})];
     end
 end
 return
 