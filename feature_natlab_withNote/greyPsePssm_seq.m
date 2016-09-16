%greyPsePssm(pssmCell,n)由pssm矩阵生成伪pssm矩阵
%pssmCell是1*L的cell型数据，存放L个蛋白质序列的PSSM矩阵
%pssmCell{i}表示第i个蛋白质的PSSM矩阵。
%返回一个L*M的矩阵（M=60或80)，矩阵的每一行向量表示一个蛋白质序列的伪pssm向量，
%该向量分两部分：前20列是PSSM矩阵的按列方向的均值,
%后40或60列由灰色模型产生。
%IF n=1,则由GM(1,1)模型产生的灰色系数作为伪氨基酸成分
%IF n=2,则由GM(2,1)模型产生的灰色系数作为伪氨基酸成分
%具体方法是以PSSM矩阵的每一列为序列构建灰色模型，由于PSSM中元素的值-7~7，
%所以建模前以函数1/(1+exp(-x))处理。
%所得的伪PSSM向量没有标准化，也没有对灰色系数考虑设置权值，这些有使用者在函数外出来
function psepssm = greyPsePssm_seq(pssm20, n)

if n == 1 %GM(1,1) generate pseudo 
    psepssm = zeros(1, 60);
        pssm = 1./(1+exp(-pssm20));
        psepssm(1:20) = mean(pssm(:, 1:20));
        for j = 1 : 20
           p = GM21Param(pssm(:, j));%背景值是否优化可以选择
           psepssm(20+2*j-1:20+2*j) = [abs(p(1)) abs(p(2))];
        end
%         psepssm(i,:) = psepssm(i,:)/sum(psepssm(i,:));
elseif n == 2 %GM(2,1) generate pseudo
    psepssm = zeros(1, 80);
        pssm = 1./(1+exp(-pssm20));
        psepssm(1:20) = mean(pssm(:, 1:20));
        for j = 1 : 20
           p = GM21Param(pssm(:, j));%背景值是否优化可以选择
           psepssm(20+3*j-2:20+3*j) = [abs(p(1)) abs(p(2)) abs(p(3))];
        end
%         psepssm(i,:) = psepssm(i,:)/sum(psepssm(i,:));
end

function P=GM21Param(X)
    n=length(X);
    X1=zeros(1,n);
    X1(1) = X(1);
    for i=2:n
        X1(i)=X1(i-1)+X(i);
    end

    Z=zeros(1,n);
    for i=2:n
        Z(i) = 0.5*(X1(i) + X1(i-1));
    end

    B=zeros(n-1,3);
    for k=1:n-1
        B(k,1) = -X(k+1);
        B(k,2) = -Z(k+1);
        B(k,3) = 1;
    end

    Y=zeros(n-1,1);
    for k=1:n-1
        Y(k) = X(k+1) - X(k);
    end
    a = inv(B'*B)*B'*Y;
    C = 0; D = 0; E = 0; F = 0; G = 0; H = 0; I = 0; L = 0;
    for k=2:n
        C = C + X(k);
        D = D + X(k)^2;
        E = E + Z(k);
        F = F + Z(k)^2;
        G = G + X(k)*Z(k);
        H = H + X(k-1)*X(k);
        I = I + X(k-1)*Z(k);
        L = X(n) - X(1);
    end

    P(1) = a(1); P(2) = a(2); P(3) = a(3);
    P(4) = C;    P(5) = D;    P(6) = E;
    P(7) = F;    P(8) = G;    P(9) = H;
    P(10) = I;   P(11) = L;
    aa=find(isnan(P)==1);
    P(aa)=0;