package com.company.p_01_iterator;

public interface Iterator {
    boolean hasNext(); // 다음요소가 존재하는지
    Object next(); // 다음 요소를 반환, 다음 실행에 대비해 미리 내부 상태를 다음으로 진행시켜둠
}
