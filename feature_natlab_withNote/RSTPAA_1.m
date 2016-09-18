
function f=RSTPAA_1(Seri,k,w)
% t1=now;
%第一列为Hydrophobicity 第二列为Hydrophilicity 第三列为Mass 第四列为pK1 第五列为pK2 第六列为pI
dataHHP=[0.6200   -0.5000   15.0000    2.3500    9.8700    6.1100
    0.2900   -1.0000   47.0000    1.7100   10.7800    5.0200
   -0.9000    3.0000   59.0000    1.8800    9.6000    2.9800
   -0.7400    3.0000   73.0000    2.1900    9.6700    3.0800
    1.1900   -2.5000   91.0000    2.5800    9.2400    5.9100
    0.4800         0    1.0000    2.3400    9.6000    6.0600
   -0.4000   -0.5000   82.0000    1.7800    8.9700    7.6400
    1.3800   -1.8000   57.0000    2.3200    9.7600    6.0400
   -1.5000    3.0000   73.0000    2.2000    8.9000    9.4700
    1.0600   -1.8000   57.0000    2.3600    9.6000    6.0400
    0.6400   -1.3000   75.0000    2.2800    9.2100    5.7400
   -0.7800    0.2000   58.0000    2.1800    9.0900   10.7600
    0.1200         0   42.0000    1.9900   10.6000    6.3000
   -0.8500    0.2000   72.0000    2.1700    9.1300    5.6500
   -2.5300    3.0000  101.0000    2.1800    9.0900   10.7600
   -0.1800    0.3000   31.0000    2.2100    9.1500    5.6800
   -0.0500   -0.4000   45.0000    2.1500    9.1200    5.6000
    1.0800   -1.5000   43.0000    2.2900    9.7400    6.0200
    0.8100   -3.4000  130.0000    2.3800    9.3900    5.8800
    0.2600   -2.3000  107.0000    2.2000    9.1100    5.6300];
% MDHHP=mean(dataHHP(:,1:3));
stdm=[std(dataHHP(:,1),1),std(dataHHP(:,2),1),std(dataHHP(:,3),1)];
Len=length(Seri);
%先读出20维的向量P
OSet='ACDEFGHIKLMNPQRSTVWY';
P=zeros(1,20);
for i=1:Len
    for j=1:20        
           if Seri(i)==OSet(j)
                P(j)=P(j)+1;
           end        
    end
end
P=P/Len;
if k==0
    f=P;
else
%读出hydrophobicity hydrophilicity side chain 读出Ji,i+k 和取前三列先试
    for i=1:k
        tao(i)=0;
        for j=1:Len-i
            num=find(OSet==Seri(j)); 
            numk=find(OSet==Seri(j+i));           
            if sum(num)~=0&sum(numk)~=0
               H1=[dataHHP(num,1),dataHHP(num,2),dataHHP(num,3)];
               H1k=[dataHHP(numk,1),dataHHP(numk,2),dataHHP(numk,3)];  
               H1=H1./stdm;
               H1k=H1k./stdm;          
               tranJ=sum((H1k-H1).^2)/3;
            else
                  tranJ=0;
            end
            tao(i)=tao(i)+tranJ;
        end
         tao(i)=tao(i)/(Len-i);
    end    
    tempaa=zeros(1,20+k);
    sumtao=sum(tao)*w;
    for i=1:20+k
        if i<21
            tempaa(i)=P(i)/(1+sumtao);
        else
            tempaa(i)=w*tao(i-20)/(1+sumtao);
        end
    end
    f=tempaa;
end
% t2=now;
% time=[datestr(t1),datestr(t2)]
return

