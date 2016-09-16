function features=ExtrKNN_Score(Datainput,DataLabel,RatioSet,blosumnum)
Len=length(DataLabel); NNset=ceil(RatioSet*Len);LenNNset=length(NNset);
MaxR=max(NNset);features=zeros(Len,LenNNset);
for i=1:Len
    Compaset=setdiff([1:Len],[i]);
    ProteinSeq1=Datainput{i,1};
    Disset4i=1000000*ones(2,MaxR);
    for j=1:Len-1
        ProteinSeq2=Datainput{Compaset(j),1};
        Disset4i=(sortrows(Disset4i'))';
        Newdis=KNNScore_Blo(ProteinSeq1,ProteinSeq2,blosumnum);
        if Newdis<Disset4i(1,end)
            Disset4i(1,end)=Newdis;
            Disset4i(2,end)=DataLabel(Compaset(j));
        end
    end
    Disset4i=(sortrows(Disset4i'))';
    for k=1:LenNNset
        PosiNNum=sum(Disset4i(2,1:NNset(k))==1);
        NegNNum=sum(Disset4i(2,1:NNset(k))==-1);
        features(i,k)=PosiNNum/(PosiNNum+NegNNum);
    end
end
return
        
        
        