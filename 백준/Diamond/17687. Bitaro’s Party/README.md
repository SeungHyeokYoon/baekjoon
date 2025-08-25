# [Diamond IV] Bitaro’s Party - 17687 

[문제 링크](https://www.acmicpc.net/problem/17687) 

### 성능 요약

메모리: 20360 KB, 시간: 252 ms

### 분류

다이나믹 프로그래밍, 두 포인터, 방향 비순환 그래프, 제곱근 분할법

### 제출 일자

2025년 8월 25일 11:39:36

### 문제 설명

<p>There are N towns of beavers numbered from 1 to N in the descending order of their heights. No two towns have the same height. There are M canals connecting two different towns unidirectionally. The i-th canal (1 ≤ i ≤ M) flows from town S<sub>i</sub> to town E<sub>i</sub>. These canals flow from high towns to low towns. You cannot move against flow of the canals.</p>

<p>Bitaro, a beaver, has N friends, one of whom lives in each of N towns.</p>

<p>Bitaro is going to have parties Q times, inviting his friends. It is known for the j-th (1 ≤ j ≤ Q) party that Y<sub>j</sub> friends are too busy to attend it. The j-th party is held in town T<sub>j</sub>, so his friends who cannot go from their towns to town T<sub>j</sub> only via canals cannot attend it either. Other friends come to the party.</p>

<p>Each friend come to the town where the party is held via canals. There may be several paths they can take. But Bitaro’s friends love canals, so they must choice one of the paths which have the largest number of canals.</p>

<p>Bitaro wonders how many canals the attendant who uses the largest number of canals uses.</p>

<p>Given the indexes of the towns where the parties are held and lists of busy friends for the Q parties, write a program which calculates how many canals the attendant who uses the largest number of canals uses.</p>

### 입력 

 <p>Read the following data from the standard input.</p>

<ul>
	<li>The first line of input contains three space separeted integers N, M, Q. These mean there are N towns of beavers and M canals and Q parties Bitaro has.</li>
	<li>The i-th line (1 ≤ i ≤ M) of following M lines contains two space separated integers S<sub>i</sub> and E<sub>i</sub>. These mean the canal flows from S<sub>i</sub> to E<sub>i</sub> unidirectionally.</li>
	<li>The j-th line (1 ≤ j ≤ Q) of following Q lines contains two space separated integers T<sub>j</sub> , Y<sub>j</sub> and Y<sub>j</sub> space separeted integers C<sub>j,1</sub>, C<sub>j,2</sub>, ..., C<sub>j,Y<sub>j</sub></sub>. These mean the j-th party is held in town T j and friends living in town C<sub>j,1</sub>, C<sub>j,2</sub>, ...,C<sub>j,Y<sub>j</sub></sub> are busy.</li>
</ul>

### 출력 

 <p>Output contains Q lines. The j-th line (1 ≤ j ≤ Q) contains the number of canals the attendant who uses the largest number of canals uses for the j-th party. If no one can attend the j-th party, the j-th line contains -1.</p>

