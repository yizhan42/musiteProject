function sequence = seqstandard(seri)
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
             t='';
        end
    S=[S,t];
end
sequence=S;

