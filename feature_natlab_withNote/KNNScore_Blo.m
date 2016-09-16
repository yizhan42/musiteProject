function KNNDis=KNNScore_Blo(ProteinSeq1,ProteinSeq2,blosumnum)
Len1=length(ProteinSeq1);
Len2=length(ProteinSeq2);
KNNDis=0;
if Len1==Len2
    OSet='ACDEFGHIKLMNPQRSTVWY';
    BM = blosum(blosumnum,'Order',OSet);
    MinM=min(min(BM));MaxM=max(max(BM));
    LengM=MaxM-MinM;
    Simab=0;
    SeqL=Len1;
     for i=1:Len1
         a=find(ProteinSeq1(i)==OSet);
         b=find(ProteinSeq2(i)==OSet);
         if length(a)*length(b)==0
            SeqL=SeqL-1;
         else
            Simab=Simab+(BM(a,b)-MinM)/LengM;
         end
     end
     KNNDis=1-Simab/SeqL;
else
    KNNDis='Wrong';
end
 return