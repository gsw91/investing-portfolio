package com.invest.mappers;

import java.util.List;

public interface BasicMapper <T, K> {

    T mapperToDomain (K k);

    List<T> mapperToListDomain (List<K> listDto);

    K mapperToDto (T t);

    List<K> mapperToListDto (List<T> listDomain);

}
