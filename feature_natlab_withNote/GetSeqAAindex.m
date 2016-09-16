 %%%%Obtaining the AAindex of AA in the seqence
 function [AAindexAll,AAindex_20,AAindex_20_std]=GetSeqAAindex(Seqinput)
 Len=length(Seqinput);
 AAindexAll=[];
 AAindex_20=[];
 AAindex_20_std=[];
 AAindexset=[ -0.591 -1.302 -0.733 1.570 -0.146;
 -1.343 0.465 -0.862 -1.020 -0.255;
 1.050 0.302 -3.656 -0.259 -3.242;
 1.357 -1.453 1.477 0.113 -0.837;
 -1.006 -0.590 1.891 -0.397 0.412;
 -0.384 1.652 1.330 1.045 2.064;
 0.336 -0.417 -1.673 -1.474 -0.078;
 -1.239 -0.547 2.131 0.393 0.816;
 1.831 -0.561 0.533 -0.277 1.648;
 -1.019 -0.987 -1.505 1.266 -0.912;
 -0.663 -1.524 2.219 -1.005 1.212;
 0.945 0.828 1.299 -0.169 0.933;
 0.189 2.081 -1.628 0.421 -1.392;
 0.931 -0.179 -3.005 -0.503 -1.853;
 1.538 -0.055 1.502 0.440 2.897;
 -0.228 1.399 -4.760 0.670 -2.647;
 -0.032 0.326 2.213 0.908 1.313;
 -1.337 -0.279 -0.544 1.242 -1.262;
 -0.595 0.009 0.672 -2.128 -0.184
 0.260 0.830 3.097 -0.838 1.512];
 AAindex=[];
for i=1:21
    AAinsum{i}=[];
end
 for i=1:Len      
     aaloc=Amino2Index(Seqinput(i));
     if aaloc==21
         AAindex(i,:)=[0,0,0,0,0];
     else
         AAindex(i,:)=AAindexset(aaloc,:);
         AAinsum{aaloc}=[AAinsum{aaloc};AAindexset(aaloc,:)];
     end
 end
for i=1:5
    AAindexAll=[AAindexAll;AAindex(:,i)];
    for j=1:21
        if size(AAinsum{j},1)~=0
           AAstd(j,1)=std(AAinsum{j}(:,i));
           AAmean(j,1)=mean(AAinsum{j}(:,i));
        else
            AAstd(j,1)=0;
            AAmean(j,1)=0;
        end
    end
    AAindex_20_std=[AAindex_20_std;AAstd];
    AAindex_20=[AAindex_20;AAstd];
end
 
 function i = Amino2Index(amino)
switch amino
    case {'A', 'a'}
        i=1;
    case {'C', 'c'}
        i=2;
    case {'D', 'd'}
        i=3;
    case {'E', 'e'}
        i=4;
    case {'F', 'f'}
        i=5;
    case {'G', 'g'}
        i=6;
    case {'H', 'h'}
        i=7;
    case {'I', 'i'}
        i=8;
    case {'K', 'k'}
        i=9;
    case {'L', 'l'}
        i=10;
    case {'M', 'm'}
        i=11;
    case {'N', 'n'}
        i=12;
    case {'P', 'p'}
        i=13;
    case {'Q', 'q'}
        i=14;
    case {'R', 'r'}
        i=15; 
    case {'S', 's'}
        i=16; 
    case {'T', 't'}
        i=17;
    case {'V', 'v'}
        i=18;
    case {'W', 'w'}
        i=19;
    case {'Y', 'y'}
        i=20; 
    otherwise
        i=21;
end