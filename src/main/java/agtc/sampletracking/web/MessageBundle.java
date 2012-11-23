package agtc.sampletracking.web;

import java.util.List;

import java.util.ListResourceBundle;

public class MessageBundle extends ListResourceBundle {
  public Object[][] getContents() {
    return contents;
  }
  static final Object[][] contents = {
	  {"sample.search.title", "Search Sample:"},
	  {"format.date", "DD-MM-YYYY"},
	  {"project.search.title", "Search Project:"},
	  {"run.search.title", "Search Run:"},
  };
}