package agtc.sampletracking.web;

import java.util.List;

import java.util.ListResourceBundle;

public class MessageBundle extends ListResourceBundle {
  public Object[][] getContents() {
    return contents;
  }
  static final Object[][] contents = {
  {"sample.search.title", "Sample Search"},
  {"format.date", "DD-MM-YYYY"},
  };
}