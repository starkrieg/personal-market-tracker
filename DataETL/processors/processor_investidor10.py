
from bs4 import BeautifulSoup

import model.standard_data_model as standard_data_model

# To be used with website
# https://investidor10.com.br/acoes/{TICKET-NAME}

def process(filePath):
    file = open(filePath, "r", encoding="utf8")

    soup = BeautifulSoup(file, "html.parser")
    
    # start data extraction
    indicators = soup.find("div", id="indicators")
    indicatorsCells = indicators.find(id="table-indicators")
    cells = indicatorsCells.find_all("div", class_="cell")

    outputData = standard_data_model.StandardizedData()

    for cell in cells:
        spans = cell.find_all("span")
        #[0] = id
        #[1] = value

        title = spans[0].text.strip()
        value = spans[1].text.strip()

        # remove % char
        value = value.replace('%', '')

        # statusInvest has number formats in d.ddd,dd
        # so normalize to dddd.dd so it can act as float in python
        value = value.replace('.', '').replace(',', '.')

        # if invalid value, store None
        if value == '-':
            value = None
        ###

        extractedData = {
            "id": title,
            "value": float(value) if value else None
        }
        
        ## bunch of if cases to parse the extracted data to the standardized object
        ## using 'elif' so it gets out of the IF blocks after seting value once
        ## maybe change to a Dict<id, function> to make things simpler?

        ### valuation
        if extractedData['id'] == 'P/L' :
            outputData.valuationData.preco_over_lucro = extractedData['value']
        elif extractedData['id'] == 'P/RECEITA (PSR)' :
            outputData.valuationData.p_over_sr = extractedData['value']
        elif extractedData['id'] == 'P/VP' :
            outputData.valuationData.p_over_vp = extractedData['value']
        elif extractedData['id'].startswith('DIVIDEND YIELD') :
            outputData.valuationData.div_yield = extractedData['value']
        elif extractedData['id'] == 'EV/EBITDA' :
            outputData.valuationData.ev_over_ebitda = extractedData['value']
        elif extractedData['id'] == 'EV/EBIT' :
            outputData.valuationData.ev_over_ebit = extractedData['value']
        elif extractedData['id'] == 'P/EBITDA' :
            outputData.valuationData.p_over_ebitda = extractedData['value']
        elif extractedData['id'] == 'P/EBIT' :
            outputData.valuationData.p_over_ebit = extractedData['value']
        elif extractedData['id'] == 'P/ATIVO' :
            outputData.valuationData.p_over_ativo = extractedData['value']
        elif extractedData['id'] == 'P/CAP.GIRO' :
            outputData.valuationData.p_over_cap_giro = extractedData['value']
        elif extractedData['id'] == 'P/ATIVO CIRC LIQ' :
            outputData.valuationData.p_over_ativo_circ_liq = extractedData['value']
        elif extractedData['id'] == 'VPA' :
            outputData.valuationData.vpa = extractedData['value']
        elif extractedData['id'] == 'LPA' :
            outputData.valuationData.lpa = extractedData['value']
        elif extractedData['id'] == 'PAYOUT' :
            outputData.valuationData.payout = extractedData['value']

        ### efficiency
        elif extractedData['id'] == 'MARGEM LÍQUIDA' :
            outputData.efficiencyData.margem_liq = extractedData['value']
        elif extractedData['id'] == 'MARGEM BRUTA' :
            outputData.efficiencyData.margem_bruta = extractedData['value']
        elif extractedData['id'] == 'MARGEM EBIT' :
            outputData.efficiencyData.margem_ebit = extractedData['value']
        elif extractedData['id'] == 'MARGEM EBITDA' :
            outputData.efficiencyData.margem_ebitda = extractedData['value']

        ### profit
        elif extractedData['id'] == 'GIRO ATIVOS' :
            outputData.profitData.giro_ativos = extractedData['value']
        elif extractedData['id'] == 'ROE' :
            outputData.profitData.roe = extractedData['value']
        elif extractedData['id'] == 'ROIC' :
            outputData.profitData.roic = extractedData['value']
        elif extractedData['id'] == 'ROA' :
            outputData.profitData.roa = extractedData['value']

        ### debt
        elif extractedData['id'] == 'DÍVIDA LÍQUIDA / PATRIMÔNIO' :
            outputData.debtData.div_liq_over_pl = extractedData['value']
        elif extractedData['id'] == 'DÍVIDA LÍQUIDA / EBITDA' :
            outputData.debtData.div_liq_over_ebitda = extractedData['value']
        elif extractedData['id'] == 'DÍVIDA LÍQUIDA / EBIT' :
            outputData.debtData.div_liq_over_ebit = extractedData['value']
        elif extractedData['id'] == 'DÍVIDA BRUTA / PATRIMÔNIO' :
            outputData.debtData.div_bruta_over_pl = extractedData['value']
        elif extractedData['id'] == 'PATRIMÔNIO / ATIVOS' :
            outputData.debtData.pl_over_ativos = extractedData['value']
        elif extractedData['id'] == 'PASSIVOS / ATIVOS' :
            outputData.debtData.pass_over_ativos = extractedData['value']
        elif extractedData['id'] == 'LIQUIDEZ CORRENTE' :
            outputData.debtData.liq_corrente = extractedData['value']

        ### growth
        elif extractedData['id'] == 'CAGR RECEITAS 5 ANOS' :
            outputData.growthData.cagr_receitas_5_anos = extractedData['value']
        elif extractedData['id'] == 'CAGR LUCROS 5 ANOS' :
            outputData.growthData.cagr_lucros_5_anos = extractedData['value']

        ### unknown
        else:
            print(f"Error: could not standardize extracted id [{extractedData['id']}]:[{extractedData['value']}] from Investidor10")
    ###

    file.close()

    return outputData