function ft=extr_prjft2_Qiu(data,traindata)
traindata=upper(traindata);data=upper(data);
[ND,MD]=size(data);
NT=size(traindata,1);
ft=zeros(ND,MD-1);
AA='ACDEFGHIKLMNPQRSTVWY';
AAset=[];count=0;
for i=1:20
    for j=1:20
        count=count+1;
        AAset{count}=strcat(AA(i),AA(j));
    end
end

for i=1:ND
    Pos=traindata;
    temNT=size(Pos,1);
    seq=data(i,:);
    for j=1:temNT
        if strcmpi(seq,Pos(j,:))
            Pos(j,:)=[];temNT=temNT-1;break;
        end
    end
% % % % % % left part % % % % % %
    for j=ceil(MD/2):-1:2
        [a,b]=ismember(seq(j:-1:j-1),AAset);
        if a==1
            %%%%calculating P(BA)
            [a1,b1]=ismember(Pos(:,j:-1:j-1),AAset{b},'rows');
            count=sum(a1==1);
            %%%%calculating P(A)
            [a2,b2]=ismember(Pos(:,j),seq(j),'rows');
            xxx=sum(a2==1);
            if xxx==0
                ft(i,j-1)=0;
            else
                ft(i,j-1)=count/xxx;
            end
        else
            ft(i,j-1)=0;
        end
    end
% % % % % % right part % % % % % %
    for j=ceil(MD/2):MD-1
        [a,b]=ismember(seq(j:j+1),AAset);
        if a==1
            %%%%calculating P(BA)
            [a1,b1]=ismember(Pos(:,j:j+1),AAset{b},'rows');
            count=sum(a1==1);
            %%%%calculating P(A)
            [a2,b2]=ismember(Pos(:,j),seq(j),'rows');
            xxx=sum(a2==1);
            if xxx==0
                ft(i,j)=0;
            else
                ft(i,j)=count/xxx;
            end
        else
            ft(i,j)=0;
        end  
    end
end
    

