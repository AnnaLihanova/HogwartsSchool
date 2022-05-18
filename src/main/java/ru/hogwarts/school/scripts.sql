package ru.hogwarts.school

select * from student where age > 11 and age <15;

select student.name from student;

select * from student where name like '%t%';

select * from  student where student.age < student.id;

select * from student ORDER BY age;