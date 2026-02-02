package com.example.apiSpotify.dto;
import java.util.List;

public class AlbumsArtists {

        private String href;
        private int limit;
        private int offset;
        private int total;
        private String next;
        private String previous;

        private List<Album> items;

        // Getters y setters
        public String getHref() { return href; }
        public void setHref(String href) { this.href = href; }

        public int getLimit() { return limit; }
        public void setLimit(int limit) { this.limit = limit; }

        public int getOffset() { return offset; }
        public void setOffset(int offset) { this.offset = offset; }

        public int getTotal() { return total; }
        public void setTotal(int total) { this.total = total; }

        public String getNext() { return next; }
        public void setNext(String next) { this.next = next; }

        public String getPrevious() { return previous; }
        public void setPrevious(String previous) { this.previous = previous; }

        public List<Album> getItems() { return items; }
        public void setItems(List<Album> items) { this.items = items; }

}