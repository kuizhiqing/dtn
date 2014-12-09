clear;clf;

%infocom2006 
x = load('xroller_vect');%time
y = load('yroller_vect');%times
x = sort(x);
y = sort(y);
k = zeros(1,length(x));
for i = 1:(length(y)-1)
    k(i) = (y(i+1)-y(i))/(x(i+1)-x(i));
end
plot(x,k,'.')
xlabel('时间'),ylabel('变化率') 