package com.evaitcs.securebank12july.service;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

@Service
public class McpBankTools {


    @McpTool(name = "getTransferLimit",
            description = "Returns the maximum daily transfer limit for SecureBank")
    public String getTransferLimit(){

        return "The maximum daily transfer limit is $5,000.";
    }


}
