package com.invest.mappers;

import java.util.List;
import java.util.function.Consumer;

public interface BasicMapper <T, K> extends Consumer {

    T mapperToDomain (K k);

    List<T> mapperToListDomain (List<K> listDto);

    K mapperToDto (T t);

    List<K> mapperToListDto (List<T> listDomain);

}
