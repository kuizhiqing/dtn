clear;clf;

x = load('mitpy');
y = linspace(1,length(x),length(x));

plot(x,y)
xlabel('时间'),ylabel('相遇次数') 