package com.clvt.jpa.records;

import java.util.List;

public record  Response<T>(List<T> data, int status) {
}
