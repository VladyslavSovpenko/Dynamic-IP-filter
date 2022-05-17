package com.example.hrtest.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Component
public class IPFilter implements Filter {

    Logger LOGGER = LoggerFactory.getLogger(IPFilter.class);

    private IPReader reader;
    private List<String> blackList;
    int entryTry = 0;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        reader = new IPReader();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        blackList = reader.read();

        HttpServletRequest req = (HttpServletRequest) request;
        String remoteAdr = req.getRemoteAddr();

        if (null == remoteAdr || remoteAdr.trim().equals("")) {
            throw new RuntimeException("IP The address is empty,access denied!");
        }

        if (!comparingIp(remoteAdr)) {

            request.setAttribute("answer", "Access allowed");
            chain.doFilter(request, response);
        } else {
            entryTry++;
            LOGGER.error("!!!Try to connect user in blacklist with ip = " + remoteAdr + ". " + entryTry + " attempt blocked.!!!");
            request.setAttribute("answer", "Access disallowed");
            chain.doFilter(request, response);
        }
    }

    private boolean comparingIp(String IP) {
        for (String IPblack : blackList) {
            if (IP.matches(IPblack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
    }


}
