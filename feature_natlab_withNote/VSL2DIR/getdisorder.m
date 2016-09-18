
function [orderans,orderscores]=getdisorder(seq,wideth)
%函数作用：获得蛋白质序列中各氨基酸的disorder的得分
seqfile='D:/VSL2DIR/tmpseqf.txt';
fid=fopen(seqfile,'w');
seq = seqstandard_Dis(seq);
fprintf(fid,'%s',seq);
fclose(fid);
cmd=sprintf('java -jar d:/vsl2dir/vsl2.jar -s:%s -w:%d >testseq.pred',seqfile,wideth);
[oderans,status]=system(cmd);
fid=fopen('testseq.pred','r');

% cmd=sprintf('cd d:/vsl2dir & d: & java -jar vsl2.jar -s:%s -w:%d >testseq.pred',seqfile,wideth);
% [oderans,status]=system(cmd);
% fid=fopen('d:/VSL2DIR/testseq.pred','r');
flag=0;
while flag==0
  line=fgetl(fid);
  if strcmp(line,'NO.     RES.    PREDICTION      DISORDER')==1
  flag=1;
  line=fgetl(fid);
  end
end
orderans=[];
orderscores=[];
while ~feof(fid)
    flag2=1;
  line=fgetl(fid);  
  row=sscanf(line,'%d %s %f \n',3);
  if strcmp(line,'========================================')==1
      flag2=0;
  end
  if length(row)==3&flag2>0
      numth=size(orderans,2)+1;
      orderans(numth).No=row(1);
      orderans(numth).AA=char(row(2));
      orderans(numth).Score=row(3); 
      orderscores=[orderscores;row(3)];
  end
end
fclose(fid);

function sequence =seqstandard_Dis(seri)
% 删除蛋白质序列中非标准氨基酸
t='';S=''; 
for i=1:length(seri);     
    switch seri(i)
        case {'A', 'a'}
            t='A';
        case {'C', 'c'}
            t='C';
        case {'D', 'd'}
            t='D';
        case {'E', 'e'}
            t='E';
        case {'F', 'f'}
            t='F';
        case {'G', 'g'}
            t='G';
        case {'H', 'h'}
            t='H';
        case {'I', 'i'}
            t='I';
        case {'K', 'k'}
            t='K';
        case {'L', 'l'}
            t='L';
        case {'M', 'm'}
            t='M';
        case {'N', 'n'}
            t='N';
        case {'P', 'p'}
            t='P';
        case {'Q', 'q'}
            t='Q';
        case {'R', 'r'}
            t='R'; 
        case {'S', 's'}
            t='S'; 
        case {'T', 't'}
            t='T';
        case {'V', 'v'}
            t='V';
        case {'W', 'w'}
            t='W';
        case {'Y', 'y'}
            t='Y'; 
        otherwise         
             t='X';
        end
    S=[S,t];
end
Datnum=[4,9,6,5,6,6,8,4,5,4,5,6,7,5,5,4,5,4,11,7];
Lab=find(S=='X');
if length(Lab)>0
    OSet='ACDEFGHIKLMNPQRSTVWY';
    for i=1:length(Lab)
        if Lab(1)==1
            x=find(OSet==S(2));
            S(1)=OSet(x);
        elseif Lab(i)==length(S)
            x=find(OSet==S(end-1));
            S(end)=OSet(x);
        else
            x1=find(OSet==S(Lab(i)-1));
            x2=find(OSet==S(Lab(i)+1));
            X=[x1,x2];
            [a,b]=max(Datnum(X));
            S(Lab(i))=OSet(X(b));
        end
    end
end    
sequence=S;